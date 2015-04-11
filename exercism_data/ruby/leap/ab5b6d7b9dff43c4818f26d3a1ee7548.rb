class Year

  def self.leap? year
    @year = year
    reduce_century
    is_divisible_by_4?
  end

  def self.is_divisible_by_4?
    @year % 4 == 0
  end

  protected

  def self.reduce_century
    if @year % 100 == 0
      @year = @year / 100
    end
  end
end
