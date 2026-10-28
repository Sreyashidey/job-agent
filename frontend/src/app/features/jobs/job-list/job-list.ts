
import { Component, OnInit, signal } from '@angular/core';
import { JobService } from '../../../core/services/job';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [],
  templateUrl: './job-list.html',
  styleUrl: './job-list.css'
})
export class JobList implements OnInit {
  jobs = signal<any[]>([]);

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    console.log('JobList component initialized');

    this.jobService.getJobs().subscribe({
      next: (response) => {
        console.log('Full API response:', response);

        this.jobs.set(response.content ?? []);

        console.log('Jobs count:', this.jobs().length);
        console.log('Jobs:', this.jobs());
      },
      error: (error) => {
        console.error('Failed to fetch jobs:', error);
      }
    });
  }
}
