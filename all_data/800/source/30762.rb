class String
  def sort
  self.split(//).sort.join
  end
end

def combine_anagrams(words)
  q = Hash.new { |q,k| q[k]= Array.new }
  groups = Array.new
  words.each{ |w|
    q[w.downcase.sort].insert(0,w)
  }
  q.each{ |a|
    groups.concat([a[1]])
  }
  return groups
end
