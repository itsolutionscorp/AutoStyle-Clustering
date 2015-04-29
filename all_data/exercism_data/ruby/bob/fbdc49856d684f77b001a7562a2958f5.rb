module TeenagerRefinements
  refine String do
    def yelling?
      self == self.upcase
    end

    def question?
      self[-1] == '?'
    end

    def only_whitespace?
      self.strip.empty?
    end
  end
end

using TeenagerRefinements

class Bob

  def hey(msg)
    return 'Fine. Be that way!' if msg.only_whitespace?
    return 'Woah, chill out!' if msg.yelling?
    return 'Sure.' if msg.question?

    'Whatever.'
  end
end
