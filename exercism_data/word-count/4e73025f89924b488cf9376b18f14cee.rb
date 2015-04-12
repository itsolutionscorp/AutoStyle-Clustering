class Phrase
  def initialize(s)
    @s = s.downcase.gsub(/[^\p{Alnum}\p\']/, ' ').split
  end

  def word_count
    @s.reduce({}) do |a, e|
      a[e].nil? && e != '' ? a[e] = 1 : a[e] += 1
      a
    end
  end
end
