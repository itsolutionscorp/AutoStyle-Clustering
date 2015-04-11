class Hamming
  def self.compute(a, b)
    raise ArgumentError, 'Arguments must be present' if a.nil? || b.nil?
    raise ArgumentError, 'Arguments should be the same length' if a.length != b.length

    hamming_difference = character_index = 0
    a.each_char do |a_char|
      hamming_difference += 1 if a_char != b[character_index]
      character_index += 1
    end

    hamming_difference
  end
end
