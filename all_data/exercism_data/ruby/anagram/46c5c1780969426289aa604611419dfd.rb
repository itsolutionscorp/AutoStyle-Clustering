class Anagram < String

  def match(haystack)
    haystack.select {|term| term.chars.sort == needle }
  end

private

  def needle
    @needle ||= self.chars.sort
  end

end
