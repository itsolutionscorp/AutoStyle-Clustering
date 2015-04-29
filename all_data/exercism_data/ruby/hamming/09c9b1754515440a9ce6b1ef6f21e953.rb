class Hamming
  def self.compute(first, second)

    (0...minimum_length([first, second])).reduce(0) do |sum, i|
      if first[i] != second[i]
        sum + 1
      else
        sum
      end
    end
  end

  private
  def self.minimum_length(strings)
    strings.map! { |string| string.length }.min
  end
end
