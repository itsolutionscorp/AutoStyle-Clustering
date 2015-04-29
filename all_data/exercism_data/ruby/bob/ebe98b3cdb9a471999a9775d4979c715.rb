class Bob
  def hey(statement)
    @statement = statement

    [:silence, :shout, :question, :statement].each do |utterance|
      response = self.send(utterance)
      return response if response
    end
  end

  private

  def silence
    "Fine. Be that way!" if @statement.gsub(/\s+/, "") == ""
  end

  def shout
    'Woah, chill out!' if @statement.upcase == @statement
  end

  def question
    "Sure." if @statement.end_with?("?")
  end

  def statement
    "Whatever."
  end
end
