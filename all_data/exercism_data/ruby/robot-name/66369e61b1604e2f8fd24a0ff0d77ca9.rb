class Robot

  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  private
    def new_name
      result = ''
      2.times { result += ('A'..'Z').to_a.sample }
      3.times { result += ('0'..'9').to_a.sample }
      result
    end
end
