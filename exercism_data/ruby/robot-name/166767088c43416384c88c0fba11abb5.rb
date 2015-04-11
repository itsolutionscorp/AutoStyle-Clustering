class Robot
  NAME_STORE = []
  attr_reader :name
  def initialize
    @name = generate_name
  end
  def reset
    @name = generate_name
  end
  private
  def generate_name
    name = ""
    if NAME_STORE.empty?
      name = "aa000"
    else
      last_name = NAME_STORE.last
      number = last_name[/\d+/].to_i + 1
      name = sprintf "aa%03d", number
    end
    NAME_STORE << name
    name
  end
end
