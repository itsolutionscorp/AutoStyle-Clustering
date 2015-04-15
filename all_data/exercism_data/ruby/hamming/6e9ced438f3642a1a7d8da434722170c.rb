class Hamming
  class << self

    def compute (strand_a, strand_b)

      dist = 0

      dist = shorter(strand_a.split(''), strand_b.split('')).each_with_index do |letter, i|
        dist += 1 if strand_a[i] != strand_b[i]
      end
      # dist

    end


    def shorter(one_thing, other_thing)
      if one_thing.length < other_thing.length
        one_thing
      else
        other_thing
      end
    end

  end
end
