class String

  def blank?
    to_s.strip == ''
  end

  def upcase?
    self == self.upcase
  end

  def question?
    end_with? '?'
  end

  def present?
    !self.blank?
  end

end

class Communication

  def initialize(person_type)
    @responses =
      case person_type
      when :teenager
        {
          silent_treatment: 'Fine. Be that way!',
          yelling: 'Woah, chill out!',
          asking_something: 'Sure.',
          default: 'Whatever.'
        }
      else
        {}
      end
  end

  def answer_to(rant)
    answer_is = nil
    @responses.each do |method, response|
      answer_is = response if send(method, rant.to_s)
      break if answer_is.to_s.present?
    end
    answer_is ||= @responses[:default] || 'Brainzz...'
  end

  private
    def silent_treatment(rant)
      rant.blank?
    end

    def yelling(rant)
      rant.upcase?
    end

    def asking_something(rant)
      rant.question?
    end

    def default(rant)
      true if rant.present?
    end

end

class Bob

  def initialize(person_type = :teenager)
    @exchange = Communication.new person_type
  end

  def hey(rant)
    @exchange.answer_to rant
  end

end
