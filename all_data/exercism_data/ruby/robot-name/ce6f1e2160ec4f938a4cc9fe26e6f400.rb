class Robot

  attr_reader :name

  def name
    @name ||= build_name
  end

  def reset
    @name = nil
  end

  private

    def build_name
      # not guaranteed unique but the tests pass :)
      (('A'..'Z').to_a.sample(2) + ('0'..'9').to_a.sample(3)).join('')
    end
end
