class Bob
  def hey(statement)
    remark = Remark.new(statement)
    if remark.silent?
      'Fine. Be that way!'
    elsif remark.shouted?
      'Woah, chill out!'
    elsif remark.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  class Remark
    def initialize(statement)
      @statement = statement
    end

    def shouted?
      @statement.upcase == @statement
    end

    def question?
      @statement.end_with? '?'
    end

    def silent?
      @statement.nil? || @statement.strip.empty?
    end
  end
end
