class Robot

  def name
    @name ||= [
      random_letter,
      random_letter,
      random_number,
      random_number,
      random_number
    ].join
  end

  def reset
    @name = nil
  end

  private

    def random_letter
      ("A".."Z").to_a.sample
    end

    def random_number
      (0..9).to_a.sample
    end
end
