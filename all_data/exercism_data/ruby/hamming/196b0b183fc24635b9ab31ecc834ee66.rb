## Starting to learn Ruby, and would like to learn best practices and Ruby idioms along the way.
## Should put a begin/rescue block at the beginning to test for string length equality (per implementation)
## details, but my code wouldn't pass the tests if I did.

class Hamming
  
  def self.compute(a,b)
    
    hamming = 0
    (0..shortest(a,b)).each {|i| hamming += 1 if a[i] != b[i]}  
    hamming
  end
  
  private

  def self.shortest(a,b)
     a.length < b.length ? a.length - 1 : b.length - 1
  end
  
end
