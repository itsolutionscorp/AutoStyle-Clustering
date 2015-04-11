## Starting to learn Ruby, and would like to learn best practices and Ruby idioms along the way.
## Should put a begin/rescue block at the beginning to test for string length equality (per implementation)
## details, but my code wouldn't pass the tests if I did.

class Hamming
  
  def self.compute(a,b)
    
    hamming = 0
    (0..shortest(a,b) - 1).each do |i|
      hamming += 1 if a[i] != b[i]
    end
      
    return hamming
  end
  
  private

  def self.shortest(a,b)
     a.length < b.length ? a.length : b.length
  end
  
end
