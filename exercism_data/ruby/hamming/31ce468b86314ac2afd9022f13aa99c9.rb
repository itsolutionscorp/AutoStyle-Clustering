class Hamming

  def self.compute(first, second)
    i = 0
    shorter = Comparator.find_shorter(first, second)
    longer = Comparator.find_longer(first, second)
    shorter.length.times do |index|
      i = i + 1 unless shorter[index] == longer[index]
    end
    i
  end

end

module Comparator

  def self.find_shorter(first, second)
    first.length > second.length ? second : first
  end

  def self.find_longer(first, second)
    first.length > second.length ? first : second
  end

end
