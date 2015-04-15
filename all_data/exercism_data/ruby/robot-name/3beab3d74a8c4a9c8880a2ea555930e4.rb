class Robot
  CHAR_RANGE = [*'A'..'Z']
  NUM_RANGE = [*'0'..'9']

  @@assigned_names = []

  def name
    @name ||= get_name
  end

  def reset
    @name = get_name
  end

  private

  def get_name
    @@assigned_names.delete(@name) if @name
    name = ''
    begin
      name = CHAR_RANGE.sample(2).join + NUM_RANGE.sample(3).join
    end while @@assigned_names.include?(name)
    @@assigned_names.push(name)
    name
  end

end
