class Year
  def self.leap?(yr)
    ((yr.divmod(4)[1] == 0) && (yr.divmod(100)[1] != 0)) || ((yr.divmod(4)[1] == 0) && (yr.divmod(400)[1] == 0)) ? true : false
  end
end

__END__

This could be a lot more readable, but it doesn't have to be.
