class Hamming
  def self.compute(first, second)
    convert(first).zip(convert(second)).inject(0) do |distance, pair| 
      match?(pair.first, pair.last) ? distance : distance + 1
    end
  end

  private
 
  def self.convert(string)
    string.split(//)
  end

  def self.match?(first, second)
    first == second
  end
end
