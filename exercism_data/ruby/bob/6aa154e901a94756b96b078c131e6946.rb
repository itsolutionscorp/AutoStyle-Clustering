# coding: utf-8

class Bob
  ANSWERS = {
    silence: 'Fine. Be that way!',
    shout: 'Woah, chill out!',
    question: 'Sure.',
    meh: 'Whatever.'
  }.freeze

  def hey(str)
    if is_silence?(str)
      ANSWERS[:silence]
    elsif is_shout?(str)
      ANSWERS[:shout]
    elsif is_question?(str)
      ANSWERS[:question]
    else
      ANSWERS[:meh]
    end
  end

  private
    def is_silence?(str)
      str.to_s.strip.empty?
    end

    def is_shout?(str)
      str.split('').all? { |ch| ch == ch.upcase }
    end

    def is_question?(str)
      str.end_with?('?')
    end
end
