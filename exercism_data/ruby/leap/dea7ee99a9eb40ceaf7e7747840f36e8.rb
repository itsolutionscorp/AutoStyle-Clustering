class Year
  def self.leap? year
    @year = year
    divisible_by_4? && not_century? || every_fourth_century
  end

  private
    def self.divisible_by_4?
      @year % 4 == 0
    end

    def self.not_century?
      @year % 100 != 0
    end

    def self.every_fourth_century
      @year % 400 == 0
    end
end
