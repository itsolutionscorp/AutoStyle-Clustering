class Gigasecond

  def self.from(time)
    self.new(born_at: time).anniversary_at
  end

  def initialize(args={})
    @born_at = args.fetch(:born_at)
  end

  def anniversary_at
    born_at + ONE_GS
  end

  private

  ONE_GS = 10e8.to_int

  attr_reader :born_at

end
