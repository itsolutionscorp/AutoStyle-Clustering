class Bob
  RESPONSES = { 
    shout: 'Woah, chill out!', 
    question: 'Sure.', 
    silent: 'Fine. Be that way!',
    statement: 'Whatever.' 
  }.freeze

  def hey remark
    RESPONSES.fetch Remark.new(remark).type
  end

  class Remark
    def initialize remark
      @remark = remark
    end

    def type
      return :shout if shout?
      return :question if question?
      return :silent if silent?
      :statement
    end

    private
    def shout?
      @remark =~ /\A[A-Z\d\W]+\z/
    end

    def question?
      @remark =~ /\?\Z/
    end

    def silent?
      @remark.nil? || @remark.empty?
    end
  end
end
