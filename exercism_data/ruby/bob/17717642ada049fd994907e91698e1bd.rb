module TeenagerStrings

  refine String do

    def yell?
      self == upcase && self != downcase
    end

    def question?
      self[-1] == '?'
    end

    def silence?
      gsub(/\s*/, '') == ''
    end

  end

end

class Bob

  using TeenagerStrings

  def hey phrase
    return 'Woah, chill out!' if phrase.yell?
    return 'Sure.' if phrase.question?
    return 'Fine. Be that way!' if phrase.silence?
    'Whatever.'
  end

end
