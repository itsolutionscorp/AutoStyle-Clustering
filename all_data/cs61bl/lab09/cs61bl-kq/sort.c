#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void sort (long values[], int n) {
	int k, j;
	long temp;
	for (k = 1; k < n; k++) {
		// Elements 0 through k-1 are in nondecreasing order:
		//   values[0] <= values[1] <= ... <= values[k-1].
		// Insert element k into its correct position, so that
		//   values[0] <= values[1] <= ... <= values[k-1].
		temp = values[k];
		for (j = k - 1; j >= 0 && values[j] > temp; j--) {
			values[j + 1] = values[j];
		}
		values[j + 1] = temp;
	}
}
	
int main (int argc, char **argv) {
	int n, k;
	long * values;
	time_t startTime, endTime;
	if (argc != 2) {
		printf ("arg should be a number");
		return 1;
	}
	n = atoi (argv[1]);
	values = malloc (n * sizeof(long));
	for (k = 0; k < n; k++) {
		values[k] = random ();
	}
	time (&startTime);
	sort (values, n);
	time (&endTime);
	printf ("%ld seconds elapsed\n", endTime-startTime);
	if (n < 20) {
		for (k = 0; k < n; k++) {
			printf ("%ld\n", values[k]);
		}
	}
	return 0;
}
