# == Hamming
# 
# The DNA alphabet consists of 4 letters: A, C, G, T
#

class Hamming
  def self.compute(s,t)
    @distance = 0

    minimum_point_mutations_for(s,t).times do |i| 
      increase_distance if there_is_no_match(s,t,i) 
    end
  
    @distance
  end

  private

  def self.minimum_point_mutations_for(s,t)
    [s.length, t.length].min
  end

  def self.there_is_no_match(s,t,i)
    s[i] != t[i]
  end

  def self.increase_distance
    @distance += 1 
  end
end
