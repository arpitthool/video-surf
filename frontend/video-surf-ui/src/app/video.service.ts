import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";
import {Observable} from "rxjs";
import {VideoDto} from "./video-dto";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File): Observable<UploadVideoResponse> {
    // create new FormData object to be passed while calling backend
    const formData = new FormData()
    // set file parameter
    formData.append('file', file, file.name)
    // make a HTTP POST to backend API
    return this.httpClient.post<UploadVideoResponse>('http://localhost:8080/api/videos', formData);
  }

  uploadThumbnail(file: File, videoId: string): Observable<string> {
    // create new FormData object to be passed while calling backend
    const formData = new FormData()
    // set file parameter
    formData.append('file', file, file.name)
    // set videoId parameter
    formData.append('videoId', videoId)
    // make HTTP POST to backend API
    return this.httpClient.post('http://localhost:8080/api/videos/thumbnail', formData, {
      responseType: 'text'
    });
  }

  getVideo(videoId: string): Observable<VideoDto> {
    // make HTTP POST to backend API
    return this.httpClient.get<VideoDto>('http://localhost:8080/api/videos/'+videoId);
  }
}
