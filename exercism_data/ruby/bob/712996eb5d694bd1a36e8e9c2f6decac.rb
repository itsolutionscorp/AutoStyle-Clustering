class Bob
  String.class_eval do
    def question?
      self.end_with?('?')
    end

    def yell?
      self == self.upcase
    end
  end

  def hey(ask)
    ask = ask.strip

    return 'Fine. Be that way!' if ask.empty?
    return 'Woah, chill out!'   if ask.yell?
    return 'Sure.'              if ask.question?

    'Whatever.'
  end
end
