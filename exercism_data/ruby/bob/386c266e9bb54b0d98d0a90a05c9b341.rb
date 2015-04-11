module TeenagerRefinements
  refine String do
    def yelling?
      self == self.upcase
    end

    def question?
      self.end_with? '?'
    end

    def silent_treatment?
      self.strip.empty?
    end
  end
end

using TeenagerRefinements

class Bob

  def hey(msg)
    return 'Fine. Be that way!' if msg.silent_treatment?
    return 'Woah, chill out!' if msg.yelling?
    return 'Sure.' if msg.question?

    'Whatever.'
  end
end
