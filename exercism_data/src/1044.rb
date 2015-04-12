class Hamming
  def compute(first, second)
    first[0, second.size].chars.zip(second.chars).inject(0) do |ham, element|
      ham += (element[0].eql? element[1]) ? 0 : 1
    end
  end
end
