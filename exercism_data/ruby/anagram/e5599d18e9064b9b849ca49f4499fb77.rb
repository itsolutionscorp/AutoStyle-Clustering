class Anagram
  def initialize(ref)
    @ref = ref.downcase
    @ref_sig = Anagram.make_sig @ref
  end

  def match(words)
    words.find_all { |w| w = w.downcase; (w != @ref) && (Anagram.make_sig(w) == @ref_sig) }
  end

  private

  def self.make_sig n
    n.each_char.sort
  end
end
