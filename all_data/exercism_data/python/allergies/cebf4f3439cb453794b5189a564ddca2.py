class Allergies:

  def __init__(self, score):
    self.score=score
    self.allergies={128:'cats', 64:'pollen', 32:'chocolate', 16:'tomatoes', 8:'strawberries', 4:'shellfish', 2:'peanuts', 1:'eggs'}
    self.keys=sorted(self.allergies, reverse=True)
    self.list=self.list()
    self.list.reverse()

  def is_allergic_to(self, item):
    results=self.list
    if(item in results):
      return True
    return False
      
  def is_sum_power_two(n):
    results = []
    results.append(128 + 64)
    results.append(128 + 64 + 32)
    results.append(128 + 64 + 32 + 16)
    results.append(128 + 64 + 32 + 16 + 8)
    results.append(128 + 64 + 32 + 16 + 8 + 4)
    results.append(128 + 64 + 32 + 16 + 8 + 4 + 2)
    results.append(128 + 64 + 32 + 16 + 8 + 4 + 2 + 1)
    return results

  def list(self):
    current=self.score
    results=[]
    if(current > self.keys[0] and current not in self.is_sum_power_two()):
      return [self.allergies.get(1)]
    while(current > self.keys[0]):
      if(self.allergies[self.keys[0]] not in results):
        results.append(self.allergies[self.keys[0]])
      current-=self.keys[0]
    for i in self.keys:
      if(i == current):
        results.append(self.allergies[i])
        return results
      elif(i < current):
        results.append(self.allergies[i])
        current-=i
    return results
