class Bob
  String.class_eval do
    def question?
      self.end_with?('?')
    end

    def upcase?
      self == self.upcase
    end
  end

  def hey(ask)
    ask = ask.strip

    return 'Fine. Be that way!' if ask.empty?
    return 'Woah, chill out!'   if ask.upcase?
    return 'Sure.'              if ask.question?

    'Whatever.'
  end
end
