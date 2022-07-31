import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File) {
    // create new FormData object to be passed while calling backend
    const formData = new FormData()
    // set file parameter
    formData.append('file', file, file.name)
    // make a HTTP POST to backend API
    return this.httpClient.post('http://localhost:8080/api/videos', formData);
  }
}
