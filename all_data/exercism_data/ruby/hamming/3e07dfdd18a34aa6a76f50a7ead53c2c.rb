class Hamming
  def self.compute(string1, string2)
    pairs = string1.split('').zip(string2.split(''))
    diff = 0
    pairs.each do |pair|
      unless pair.include? nil
      diff += 1 if pair[0] != pair[1]
      end
    end
    return diff
  end

end
