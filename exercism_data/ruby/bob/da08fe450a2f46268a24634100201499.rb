class Bob

  def hey remark
    statement = Remark.new remark

    case
      when statement.is_a_freak_out? then 'Whoa, chill out!'
      when statement.is_a_question? then 'Sure.'
      when statement.is_silence? then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end

end

class Remark

  def initialize statement
    @statement = statement
  end

  def is_a_freak_out?
    @statement.upcase == @statement && @statement.downcase != @statement
  end

  def is_a_question?
    @statement[-1] == '?'
  end

  def is_silence?
    @statement.gsub(/\s+/, '').empty?
  end
end
