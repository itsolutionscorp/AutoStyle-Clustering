class Anagram
  def initialize(ana)
    @anagram = ana
  end

  def match(arr)
    match_letter_counts = {}

    arr.each do |x|
      match_letter_counts[x] = x.downcase.split('').each_with_object(Hash.new(0)) do |e, a|
        a[e] += 1
      end
    end

    anagram_letter_counts = @anagram.downcase.split('').each_with_object(Hash.new(0)) { |e, a| a[e] += 1 }

    match_letter_counts.reject { |k, v| v != anagram_letter_counts || k.downcase == @anagram.downcase }.keys
  end
end

__END__

People were commenting on my one-liners (especially nested one-liners),
so I tried to make this a little more readable.
