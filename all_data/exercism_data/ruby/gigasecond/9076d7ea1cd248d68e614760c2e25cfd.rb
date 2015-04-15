module Gigasecond

  def self.from(birthday)
    birthday + ONE_GIGASECOND
    birthday + one_gigasecond
  end

  private
  ONE_GIGASECOND = 10 ** 9
  private_constant :ONE_GIGASECOND

  def self.one_gigasecond
    10 ** 9
  end
end
