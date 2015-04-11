class Gigasecond  # create a class named Gigasecond

  def Gigasecond.from(date)  # create a method named from which is a class method and takes date as a parameter

    seconds = date.to_time.to_i  # convert date to seconds

    gig = seconds + (10**9)  # convert seconds into gigaseconds

    time = Time.at(gig)  # convert gigaseconds into time/date

    return_date = time.to_date  # convert time/date into date format

    return return_date  # return the formatted date

  end



end
