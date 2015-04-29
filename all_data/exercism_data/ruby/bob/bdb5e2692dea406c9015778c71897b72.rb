module UnimplementedLanguageProcessing
  def address(communication_type)
    "Sorry, I'm not programmed for language processing yet."
  end
end

class Baby; include UnimplementedLanguageProcessing; end
class Child; include UnimplementedLanguageProcessing; end
class YoungAdult; include UnimplementedLanguageProcessing; end
class MiddleAged; include UnimplementedLanguageProcessing; end
class Elderly; include UnimplementedLanguageProcessing; end


class Teenager

  RESPONSES = {
    :silence => "Fine. Be that way!",
    :question => "Sure.",
    :shout => "Woah, chill out!"
  }

  def address(communication_type)
    RESPONSES[communication_type] || "Whatever."
  end

end


class Person

  attr_accessor :age

  AGE_GROUPS = {
    (0..3) => Baby,
    (4..12) => Child,
    (13..19) => Teenager,
    (20..35) => YoungAdult,
    (36..64) => MiddleAged,
    (65..1000) => Elderly
  }

  def initialize(options = {})
    @age = options[:age] || 30
  end

  def age_group
    AGE_GROUPS[AGE_GROUPS.keys.find {|group| group.include? age}]
  end

  def address(communication)
    age_group.new.address(CommunicationAnalyzer.new(communication).type)
  end

end


class CommunicationAnalyzer

  attr_reader :communication

  def initialize(communication)
    @communication = communication.gsub(" ", "")
  end

  def type
    if silence?
      :silence
    elsif question?
      :question
    elsif shout?
      :shout
    end
  end

  def shout?
    !silence? && communication.upcase == communication
  end

  def question?
    !shout? && communication[-1] == "?"
  end

  def silence?
    communication.size == 0
  end

end
