from collections import defaultdict

class Garden:
    def __init__(self, plants, students=('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry')):
        self._garden = defaultdict(list)
        
        students = tuple(sorted(students)) 
        flowers = dict( (f[0], f) for f in ('Grass', 'Clover', 'Radishes', 'Violets'))
        for row in plants.split('\n'):
            for i, f in enumerate(row):
                self._garden[students[i/2]].append(flowers[f])
    
    def plants(self, who):
        return self._garden[who]

if __name__ == '__main__':
    print Garden('VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV')._garden
    
