require 'pry-byebug'
class Bob

  RESPONSES = {
    question: 'Sure.',
    yell: 'Whoa, chill out!',
    blank: 'Fine. Be that way!',
    other: 'Whatever.'
  }

  def hey(remark)
    remark_type = check_type(remark)
    RESPONSES[remark_type]
  end

  def check_type(remark)
    if !!remark.match(/[A-Z]/)
      if remark == remark.upcase
        :yell
      elsif remark.chars.last == '?'
        :question
      else
        :other
      end
    else
      if remark.chars.last == '?'
        :question
      elsif !!remark.match(/[a-zA-Z0-9]/) == false
        :blank
      else
        :other
      end

    end
  end
end
