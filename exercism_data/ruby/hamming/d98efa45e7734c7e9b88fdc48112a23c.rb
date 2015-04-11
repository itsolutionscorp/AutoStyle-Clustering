class Hamming
  def self.compute(a, b)
    targets = to_array(a)
    sources = to_array(b)
    diff    = 0

    targets.each_with_index do |target, index|
      if target != sources[index] && !sources[index].nil?
        diff += 1 
      end
    end 

    diff
  end

  def self.to_array(a_string)
    a_string.split('')
  end
end
