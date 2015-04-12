class Phrase
  def initialize(s)
    @s = s.downcase.gsub(/[^\p{Alnum}\p']/, ' ').split
  end

  def word_count
    @s.reduce({}) do |a, e|
      if a[e].nil? && e != ''
        a[e] = 1
      else
        a[e] += 1
      end
      a
    end
  end
end
