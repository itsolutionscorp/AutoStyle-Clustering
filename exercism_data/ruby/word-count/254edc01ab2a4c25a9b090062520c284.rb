class Phrase
  def initialize(s)
    @s = s
  end

  def word_count
    @s.downcase!
    @s.gsub!(/[!@#$%^&*]/, "")
    words = @s.split(/[\s,:\.]/)
    words.delete("")
    h = {}
    words.uniq.each { |w| h[w] = words.count(w) }
    h
  end
end
