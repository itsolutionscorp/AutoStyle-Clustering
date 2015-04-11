class Robot
  A     = ?A.ord
  NAMES = { nil => nil }

  attr_writer :name

  def initialize
    self.name = nil
  end

  def name
    @name ||= begin
                s = "%s%s%03d" % [(A + rand(26)).chr,
                                  (A + rand(26)).chr,
                                  rand(1000)] while NAMES.has_key? s
                NAMES[s] = s
              end
  end

  def reset
    NAMES.delete name if name
    self.name = nil
  end
end
