class Hamming
  def self.compute(*strands)
    relevant_chars = strands.map(&:length).min
    result = 0
    for i in 0...relevant_chars
      strands[0][i] == strands[1][i] ? nil : result += 1
    end
    result
  end
end
