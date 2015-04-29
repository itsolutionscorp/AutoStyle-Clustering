class Bob  
  attr_accessor :remark, :remark_type

  CONVERSATIONS = {
    :question => 'Sure.',
    :generic => 'Whatever.',
    :yell => 'Whoa, chill out!',
    :passive_aggressive => 'Fine. Be that way!'
  }

  def initialize(remark)
    @remark = remark
    @remark_type = determine_remark_type(remark)
  end

  def hey
    CONVERSATIONS.select {|type, reply| reply if type == remark_type}.values.first
  end

  def determine_remark_type(remark)
    if remark.gsub(/[^a-zA-Z]/, '').upcase == remark.gsub(/[^a-zA-Z]/, '') && !remark.gsub(/[^a-zA-Z]/, '').empty?
      :yell
    elsif remark[-1] == '?'
      :question
    elsif remark.gsub(/[^a-zA-Z0-9]/, '').empty?
      :passive_aggressive
    else
      :generic
    end
  end
end
