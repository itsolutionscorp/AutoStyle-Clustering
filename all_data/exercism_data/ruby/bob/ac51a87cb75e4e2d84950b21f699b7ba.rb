module TalkLikeATeenager 

  def exclaims_fine_be_that_way
    "Fine. Be that way!"
  end

  def exclaims_woah_chill_out
    "Woah, chill out!"
  end

  def says_sure
    "Sure."
  end

  def says_whatever
    "Whatever."
  end

end

module ParseLikeATeenager

  class ::String

    def is_shouting?
      self === self.upcase
    end

    def is_a_question?
      self.match(/\?$/)
    end

    def is_forceful?
      self.end_with?("!")
    end

    def is_a_forceful_question?
      self === self.upcase && self.end_with?("?")
    end

  end

end

class Bob

  include TalkLikeATeenager 
  include ParseLikeATeenager

  def hey(a)
    if a.nil? || a.strip.empty?
      exclaims_fine_be_that_way
    else
      responds_to(a.strip)
    end
  end

  def responds_to(a)
    case 
      when a.is_shouting? || a.is_a_forceful_question?
        exclaims_woah_chill_out
      when a.is_a_question?
        says_sure
      else
        says_whatever
    end
  end

end
