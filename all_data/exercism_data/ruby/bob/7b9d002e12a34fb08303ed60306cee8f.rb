module Speach
  refine String do
    def shouting?
      self[/[[:upper:]]/] && self[/\A[^[:lower:]]*\z/]
    end

    def question?
      end_with?('?')
    end

    def silence?
      strip.empty?
    end
  end
end

class Bob
  using Speach

  def hey(remark)
    case
    when remark.shouting? then 'Whoa, chill out!'
    when remark.question? then 'Sure.'
    when remark.silence? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end
