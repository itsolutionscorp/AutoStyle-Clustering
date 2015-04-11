class Robot

  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  private
    def new_name
      result = String.new.tap do |x|
        2.times { x << random_letter }
        3.times { x << random_number }
      end
    end

    def random_letter
      range_sample('A'..'Z')
    end

    def random_number
      range_sample('0'..'9')
    end

    def range_sample(range)
      range.to_a.sample
    end
end
