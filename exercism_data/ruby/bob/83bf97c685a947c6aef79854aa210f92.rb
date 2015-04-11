class Bob
  def hey(heard_string)
    utterance = Utterance.new(heard_string)
    if utterance.blank?
      "Fine. Be that way."
    elsif utterance.questioning?
      "Sure."
    elsif utterance.shouting?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  private

  class Utterance
    def initialize(content)
      @content = content
    end

    def shouting?
      content.upcase == content
    end

    def questioning?
      content.end_with?("?")
    end

    def blank?
      content.nil? || content.empty?
    end

    private

    attr_reader :content
  end
end
