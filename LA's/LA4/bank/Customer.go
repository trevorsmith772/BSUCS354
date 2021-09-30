package main

type Customer struct {
	name string
}

func (c *Customer) toString() string {
	return c.name
}
