class Robot

  def name
    @name ||= "#{model}#{version}"
  end

  def reset
    @name = nil
  end

  private

    def version
      rand(100..1000)
    end

    def model
      Array('A'...'Z').sample(2).join('')
    end
end
