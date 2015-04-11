class Bob
  def hey(phrase)
    if yelled_at?(phrase = nil)
      'Woah, chill out!'
    elsif asked?(phrase)
      'Sure.'
    elsif phrase.empty?
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  private
    def yelled_at?(phrase)
     phrase.upcase!.nil?
    end

    def asked?(phrase)
      phrase.chars.last == '?'
    end
end
