class Hamming
  class << self
    def compute(first, second)
      first_chars = first.split('')
      second_chars = second.split('')

      first_chars.zip(second_chars).count do |a, b|
        a && b && a != b
      end
    end
  end
end
